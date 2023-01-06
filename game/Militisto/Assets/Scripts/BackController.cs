using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BackController : MonoBehaviour {
    [SerializeField] float speed = 1;
    void Start() {

    }

    // Update is called once per frame
    void Update() {
        transform.position -= new Vector3(Time.deltaTime * speed, 0);

        //Yが-11まで来れば、21.33まで移動する
        if (transform.position.x <= -17.8176f) {
            transform.position = new Vector2(17.8176f, 0);
        }
    }
}
