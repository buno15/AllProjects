<template>
  <div id="itembody" @click="onClick">
    <img id="icon" :src="img" />
    <h1 id="itemtitle">{{ dataName }}</h1>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  props: {
    dataID: String,
    dataName: String,
    dataImg: String,
    dataLink: String,
    extLink: Boolean,
  },
  setup(props) {
    const router = useRouter();
    const img = require(`../assets/img/${props.dataImg}.png`);

    const onClick = () => {
      if (!props.extLink) {
        router.push({ name: "Description", params: { id: props.dataID } });
      } else {
        window.open(props.dataLink, "_blank");
      }
    };

    return {
      img,
      onClick,
    };
  },
};
</script>
<style>
#itembody {
  width: 40%;
  height: 120px;
  background-color: #ffffff;
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 100px auto 20px auto;
  cursor: pointer;
}
#itembody:hover {
  background-color: #dddddd;
}

#icon {
  width: 100px;
  height: 100px;
  margin: 5px 5px 5px 20px;
}
#itemtitle {
  font-size: 24px;
  margin: auto 30px;
}
</style>