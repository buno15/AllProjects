<template>
  <div id="descbody">
    <div id="nameBody">
      <img id="appIcon" :src="img" />
      <h1 id="appName">{{ appName }}</h1>
    </div>
    <div class="topic">
      <h2 class="topicTitle">ストアページ</h2>
      <p class="topicText">
        <a :href="link" target="_blank">{{ link }}</a>
      </p>
    </div>
    <div class="topic" v-for="arr in array" :key="arr">
      <h2 class="topicTitle">{{ arr.head }}</h2>
      <p class="topicText" v-html="arr.desc.replace(/\n/g, '<br/>')"></p>
    </div>
  </div>
</template>
<script>
import { reactive, ref } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";

export default {
  name: "Description",
  components: {},
  setup() {
    useStore().commit("onclick", 1);

    window.scrollTo({
      top: 0,
      behavior: "instant",
    });

    const route = useRoute();
    const routeID = route.params.id;

    const pageApp = useStore().state.pageApp;
    var pageID = ref(-1);

    for (let i = 0; i < pageApp.length; i++) {
      if (pageApp[i].id === routeID) {
        pageID = i;
      }
    }

    const appName = pageApp[pageID].name;
    const img = require(`../assets/img/${pageApp[pageID].img}.png`);
    const link = pageApp[pageID].link;

    const pageTopic = useStore().state.pageTopic[pageID];

    const array = reactive([]);

    for (let i = 0; i < pageTopic.head.length; i++) {
      array.push({
        head: String(pageTopic.head[i]),
        desc: String(pageTopic.desc[i]),
      });
    }

    return { route, img, pageID, appName, link, pageTopic, array };
  },
};
</script>
<style>
#nameBody {
  width: 50%;
  height: 120px;
  background-color: #ffffff;
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 100px auto 20px auto;
}
#appIcon {
  width: 100px;
  height: 100px;
  margin: 5px 5px 5px 30px;
}

#appName {
  width: 75%;
  font-size: 30px;
  margin: auto auto auto 0px;
}

.topic {
  width: 80%;
  margin: 0px auto 0px auto;
}
.topicTitle {
  width: 100%-40px;
  margin: 70px auto 30px auto;
  padding: 10px 0px 10px 40px;
  background-color: #fffacd;
  font-size: 22px;
  text-align: left;
}
.topicText {
  width: 90%;
  margin: 0px auto;
  font-size: 18px;
  text-align: left;
  white-space: pre-wrap;
}
</style>
