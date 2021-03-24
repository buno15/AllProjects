var sound_start = new Audio('res/raw/start.mp3');

var app = new Vue({
    el : "#app",
    data : {
        Disabled1 : false,
        Disabled2 : false,
    },

    mounted : function() {
        var n_flag = localStorage.getItem("n_flag");
        console.log(n_flag);
        if (n_flag == "" || n_flag == undefined || n_flag == "1" || n_flag == null) {
            this.Disabled1 = false;
            this.Disabled2 = true;
            localStorage.setItem("hp", -1);
            localStorage.setItem("damage", -1);
            localStorage.setItem("power", -1);
            localStorage.setItem("brain", -1);
            localStorage.setItem("item_have1", "なし");
            localStorage.setItem("item_have2", "なし");
            localStorage.setItem("enemyID", "none");
        } else if (n_flag == "2") {
            this.Disabled1 = true;
            this.Disabled2 = false;
        }
    },

    methods : {
        new_game : function() {
            sound_start.play();
            localStorage.setItem("n_flag", "1");
            setTimeout(this.start, 3500);
        },
        load_game : function() {
            sound_start.play();
            localStorage.setItem("n_flag", "2");
            setTimeout(this.start, 3500);
        },

        start : function() {
            location.href = "src/html/play.html";
        },
    },
});
