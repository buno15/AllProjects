var Item = function(name, value, level) {
	if (!(this instanceof Item)) {
		return new Item(name, value, level);
	}
	this.name = name;
	this.value = value;
	this.level = level;
};

Item.prototype = {
	getName : function() {
		return this.name;
	},

	getValue : function() {
		return this.value;
	},

	getLevel : function() {
		return this.level;
	},
};



