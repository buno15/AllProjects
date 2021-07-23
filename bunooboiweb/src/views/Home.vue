<template>
  <div id="home">
    <div class="topic">
      <h2 class="topicTitle">About</h2>
      <p class="topicText" v-html="about"></p>
    </div>
    <div class="topic">
      <h2 class="topicTitle">Contact</h2>
      <p class="topicText" v-html="contact"></p>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { useStore } from "vuex";

export default {
  name: "Home",
  components: {},
  setup() {
    useStore().commit("onclick", 0);

    const about =
      "こちらはbunooboiの紹介サイトです。主に自作Androidアプリケーションの紹介を行っています。<br>同時に自作ウェブアプリケーションを紹介しています。<br>主な使用言語はJavaです。その他にKotlin、JavaScript、Vue.js、PHPを使用しています。";

    const contact =
      'Mail: <a href="mailto:bunooboi@gmail.com" target="_blank">bunooboi@gmail.com</a><br><br>Twitter: <a href="https://twitter.com/bunooboi" target="=blank">bunooboi</a><br><br>Youtube: <a href="https://www.youtube.com/channel/UCNCkvqAkBIFCI1TDSv3DEXA" target="_blank">bunooboi video</a><br>';

    return { about, contact };
  },
};
</script>
<style>
#home {
  width: 100%;
  background-color: #ccffff;
}
.table {
  width: 80%;
  display: flex;
  margin: 0px auto;
}
</style>