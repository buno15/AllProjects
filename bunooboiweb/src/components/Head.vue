<template>
  <div>
    <img id="head" src="../assets/img/title.png" @click="goHome" />
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default {
  name: "Head",
  setup() {
    const router = useRouter();
    const store = useStore();

    const goHome = () => {
      router.push("/");
      store.commit("onclick", 0);
    };

    return { store, goHome };
  },
};
</script>
<style>
img#head {
  cursor: pointer;
}
</style>