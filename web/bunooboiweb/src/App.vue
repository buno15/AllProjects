<template>
  <div id="main">
    <Head id="head" />
    <div id="pagebody">
      <ul>
        <li>
          <router-link
            class="menu"
            to="/"
            @click="onclick(0)"
            v-bind:class="{ menuclick: data.store.state.click[0] }"
            >Home</router-link
          >
        </li>
        <li>
          <router-link
            class="menu"
            to="/appgame"
            @click="onclick(1)"
            v-bind:class="{ menuclick: data.store.state.click[1] }"
            >App/Game</router-link
          >
        </li>
        <li>
          <router-link
            class="menu"
            to="/webapp"
            @click="onclick(2)"
            v-bind:class="{ menuclick: data.store.state.click[2] }"
            >WebApplication</router-link
          >
        </li>
        <li></li>
      </ul>
      <router-view name="body" id="body" />
      <div id="copy">
        <small>&copy; bunooboi</small>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import Head from "@/components/Head.vue";
import { useStore } from "vuex";

export default {
  name: "App",
  components: {
    Head,
  },
  setup() {
    document.title = "bunooboi";

    const data = reactive({
      store: useStore(),
    });

    const onclick = (id) => {
      data.store.commit("onclick", id);
    };

    return {
      data,
      onclick,
    };
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  word-break: break-all;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
}

#head {
  margin: 20px auto;
}

#main {
  width: 100%;
}

#main a {
  font-weight: bold;
  color: #2c3e50;
}

#main ul {
  width: 100%;
  height: 60px;
  margin: 10px 0 0px 0;
  /*border: 1px solid;*/
}

#main ul li {
  width: 33.333%;
  list-style-type: none;
  display: inline;
  float: left;
  font-size: 20px;
  background-color: #ffffff;
}

.menu {
  height: 40px;
  display: block;
  padding: 20px 0px 2px 0px;
  text-decoration: none;
  text-align: center;
}

.menuclick {
  background-color: #d6f0ff;
}

#pagebody {
  width: 80%;
  margin: 0px auto 100px auto;
}

#body {
  width: 100%-1px;
  margin: 0px auto;
  padding-bottom: 150px;
  background-color: #d6f0ff;
  border: 1px solid #d6f0ff;
}
#copy {
  margin: 75px auto 20px auto;
  font-size: 20px;
}
</style>
