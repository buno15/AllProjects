<template>
  <div class="webapp">
    <div class="table" v-for="(items, index) in ArrayApp" :key="index">
      <Item
        v-for="p in items"
        :key="p.name"
        :dataID="p.id"
        :dataName="p.name"
        :dataImg="p.img"
        :dataLink="p.link"
        :extLink="true"
      />
    </div>
  </div>
</template>
<script>
import Item from "@/components/Item.vue";
import { useStore } from "vuex";
import { reactive } from "vue";

export default {
  name: "WebApp",
  components: { Item },
  setup() {
    useStore().commit("onclick", 2);

    const webApp = reactive(useStore().state.webApp);

    const slice = 2;

    const ArrayApp = [];
    for (let i = 0; i < Math.ceil(webApp.length / slice); i++) {
      let multiple_cnt = i * slice;
      let result = webApp.slice(multiple_cnt, multiple_cnt + slice);
      ArrayApp.push(result);
    }

    return { webApp, ArrayApp };
  },
};
</script>
<style>
</style>
