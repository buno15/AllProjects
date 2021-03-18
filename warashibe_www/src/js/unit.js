var Unit = function(name, value, level) {
	if (!(this instanceof Unit)) {
		return new Player(name, value, level);
	}
	this.name = name;
	this.value = value;
	this.level = level;
}; 