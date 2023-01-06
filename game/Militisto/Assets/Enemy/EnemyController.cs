using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyController : MonoBehaviour {
    Rigidbody2D rbody;
    GameObject player;
    float axisH = 0.0f;
    public float speed = 2.0f;
    public float jump = 9.0f;
    public LayerMask groundLayer;
    bool onGround = false;
    bool isMoving = false;
    bool onAttack = false;
    Animator animator;
    public string idleAnime = "EnemyIdle";
    public string runAnime = "EnemyRun";
    public string jumpAnime = "EnemyJump";
    public string fallAnime = "EnemyFall";
    public string attack1Anime = "EnemyAttack";
    public string damageAnime = "EnemyDamage";
    public string deadAnime = "EnemyDeath";

    string nowAnime = "";
    string oldAnime = "";
    bool isDamage = false;
    void Start() {
        rbody = this.GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        nowAnime = idleAnime;
        oldAnime = idleAnime;
    }


    void Update() {
        player = GameObject.FindGameObjectWithTag("Player");
        if (player != null) {
            //プレイヤーへの角度を求める
            float dx = player.transform.position.x - transform.position.x;
            float dy = 0;
            float rad = Mathf.Atan2(dy, dx);
            //移動角度でアニメーションを変更する
            if (dx < 0) {
                transform.localScale = new Vector2(-5f, 5f);
            } else {
                transform.localScale = new Vector2(5f, 5f);
            }
            //移動するベクトルを作る
            axisH = Mathf.Cos(rad) * speed;
        }
    }

    void FixedUpdate() {
        onGround = Physics2D.Linecast(transform.position / 2, transform.position / 2 - (transform.up * 0.01f), groundLayer);

        if (!isDamage) {
            rbody.velocity = new Vector2(axisH, rbody.velocity.y);
            nowAnime = runAnime;

            if (nowAnime != oldAnime) {
                oldAnime = nowAnime;
            }
        }

        animator.Play(nowAnime);
    }
    public void damage() {
        rbody.velocity = new Vector2(0, 0);
        isDamage = true;
        nowAnime = damageAnime;
        Vector3 toPos = (transform.position - player.transform.position).normalized;
        rbody.AddForce(new Vector2(toPos.x * 2,
                                   toPos.y * 2),
                                   ForceMode2D.Impulse);
    }

    public void stopDamage() {
        isDamage = false;
    }
}
