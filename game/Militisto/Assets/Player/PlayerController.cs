using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {
    Rigidbody2D rbody;
    float axisH = 0.0f;
    public float speed = 9.0f;
    public float jump = 9.0f;
    public float attackRange = 0.5f;
    public Transform attackPoint;
    public LayerMask groundLayer;
    public LayerMask enemyLayer;
    int countJump = 0;
    bool clickJump = false;
    bool onGround = false;
    bool isMoving = false;
    bool onAttack1 = false;
    bool onAttack2 = false;
    bool onDamage = false;
    Animator animator;
    public string idleAnime = "PlayerIdle";
    public string runAnime = "PlayerRun";
    public string jumpAnime = "PlayerJump";
    public string fallAnime = "PlayerFall";
    public string attack1Anime = "PlayerAttack1";
    public string attack2Anime = "PlayerAttack2";
    public string damageAnime = "PlayerDamage";
    public string deadAnime = "PlayerOver";
    string nowAnime = "";
    string oldAnime = "";
    void Start() {
        rbody = this.GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        nowAnime = idleAnime;
        oldAnime = idleAnime;
    }

    // Update is called once per frame
    void Update() {
        axisH = Input.GetAxisRaw("Horizontal");
        if (axisH > 0.0f) {
            transform.localScale = new Vector2(6f, 6f);
        } else if (axisH < 0.0f) {
            transform.localScale = new Vector2(-6f, 6f);
        }

        if (countJump <= 0) {
            if (Input.GetKeyDown(KeyCode.Space)) {
                countJump++;
                rbody.velocity = new Vector2(rbody.velocity.x, 0);
                Vector2 jumpPw = new Vector2(0, jump);
                rbody.AddForce(jumpPw, ForceMode2D.Impulse);
            }
        }

        if (Input.GetMouseButton(0)) {
            Attack1();
        }
        if (Input.GetMouseButton(1)) {
            Attack2();
        }
    }
    void FixedUpdate() {
        onGround = Physics2D.Linecast(transform.position / 2, transform.position / 2 - (transform.up * 0.01f), groundLayer);

        if (onGround) {
            countJump = 0;
        }
        if ((onGround || axisH != 0) && !onDamage) {
            rbody.velocity = new Vector2(speed * axisH, rbody.velocity.y);
        }


        if (onDamage) {
            nowAnime = damageAnime;
        } else if (onAttack1) {
            nowAnime = attack1Anime;
            rbody.velocity = new Vector2(0, 0);
        } else if (onAttack2) {
            nowAnime = attack2Anime;
            rbody.velocity = new Vector2(0, 0);
        } else if (onGround) {
            if (axisH == 0) {
                nowAnime = idleAnime;
            } else {
                nowAnime = runAnime;
            }
        } else {
            if (rbody.velocity.y < 0) {
                nowAnime = fallAnime;
            } else
                nowAnime = jumpAnime;
        }
        if (nowAnime != oldAnime) {
            oldAnime = nowAnime;
            animator.Play(nowAnime);
        }
    }
    public void Attack1() {
        if (onGround && !onAttack2 && !onDamage) {
            onAttack1 = true;
            Collider2D[] hitEnemies = Physics2D.OverlapCircleAll(attackPoint.position, attackRange, enemyLayer);

            foreach (Collider2D enemy in hitEnemies) {
                Debug.Log("hit");
                enemy.GetComponent<EnemyController>().damage();
            }
        }
    }
    public void Attack2() {
        if (onGround && !onAttack1 && !onDamage) {
            onAttack2 = true;
            Collider2D[] hitEnemies = Physics2D.OverlapCircleAll(attackPoint.position, attackRange * 2, enemyLayer);

            foreach (Collider2D enemy in hitEnemies) {
                Debug.Log("hit");
                enemy.GetComponent<EnemyController>().damage();
            }
        }
    }
    public void stopAttack1() {
        onAttack1 = false;
    }
    public void stopAttack2() {
        onAttack2 = false;
    }

    public void damage(GameObject enemy) {
        onDamage = true;
        Vector3 toPos = (transform.position - enemy.transform.position).normalized;
        rbody.AddForce(new Vector2(toPos.x * 8,
                                   toPos.y * 8),
                                   ForceMode2D.Impulse);
    }
    public void stopDamage() {
        onDamage = false;
    }

    private void OnCollisionEnter2D(Collision2D collision) {
        if (collision.gameObject.tag == "Enemy") {
            damage(collision.gameObject);
        }
    }

    void OnDrawGizmosSelected() {
        if (attackPoint == null)
            return;
        if (onAttack1)
            Gizmos.DrawWireSphere(attackPoint.position, attackRange);
        else if (onAttack2)
            Gizmos.DrawWireSphere(attackPoint.position, attackRange * 2);
    }
}
