var app = new Vue({
	el : "#app",
	data : {
		status : 1,
		image_src : "res/img/sougenn.png",
		left : "地平線に向かって走り続ける",
		right : "暗雲に向かって走り続ける",
		console_text : "どこへ行こう？",
		damage_text : damage,
		hp_text : hp,
		power_text : power,
		brain_text : brain,
		item_text : item_have.getName(),
	},
	methods : {
		next : function() {
			alert(this.status);
			if (this.status == 1) {
				this.status = 2;
				this.image_src = "res/img/oihagi.png";
			} else {
				hp = 100;
				this.status = 1;
				save();
				this.image_src = "res/img/sougenn.png";
			}
		},
	},
}); 