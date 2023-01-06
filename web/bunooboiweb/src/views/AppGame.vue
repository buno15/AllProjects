<template>
  <div id="home">
    <div class="table" v-for="(items, index) in ArrayApp" :key="index">
      <Item
        v-for="p in items"
        :key="p.name"
        :dataID="p.id"
        :dataName="p.name"
        :dataImg="p.img"
        :dataLink="p.link"
        :extLink="false"
      />
    </div>
  </div>
</template>

<script>
import Item from "@/components/Item.vue";
import { useStore } from "vuex";
import { reactive } from "vue";

export default {
  name: "Home",
  components: {
    Item,
  },
  setup() {
    useStore().commit("onclick", 1);

    const pageApp = reactive(useStore().state.pageApp);

    const slice = 2;

    const ArrayApp = [];
    for (let i = 0; i < Math.ceil(pageApp.length / slice); i++) {
      let multiple_cnt = i * slice;
      let result = pageApp.slice(multiple_cnt, multiple_cnt + slice);
      ArrayApp.push(result);
    }

    return { pageApp, ArrayApp };
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