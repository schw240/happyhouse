<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="instagram"></b-icon> 캣독그램 Service
    </h3>
    <router-view></router-view>
    <!-- 등록버튼 누르면 입력화면 창으로 이동 -->
    <b-row>
      <b-col></b-col>
      <b-col cols="12"> </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "CatDoggram",
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
</style>
