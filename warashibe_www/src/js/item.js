class Item {
	constructor(name, value, level) {
		this.name = name;
		this.value = value;
		this.level = level;
	}

	getName() {
		console.log(this.name);
	}

}

const item = new Item("木の枝", 12);

item.getName();
//Good Morning!!

