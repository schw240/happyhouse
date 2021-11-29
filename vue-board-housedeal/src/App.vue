<template>
  <div id="app">
    <navi-bar />
    <scroll-top />
    <router-view />
    <footer-layer />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";
import scrollTop from "./components/layout/scrollTop.vue";
import FooterLayer from "./components/layout/FooterLayer.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    scrollTop,
    FooterLayer,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: rgba(148, 73, 73, 0.808);
  font-weight: bold;
}
</style>
