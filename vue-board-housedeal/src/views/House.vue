<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange" style="margin-bottom: 50px">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>

    <b-row>
      <b-col cols="6">
        <b-row>
          <house-search-bar></house-search-bar>
        </b-row>
        <b-row>
          <house-list />
        </b-row>
      </b-col>
      <b-col cols="6">
        <kakao-map></kakao-map>
        <house-detail />
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6"></b-col>
      <b-col cols="6"> </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import KakaoMap from "@/components/house/KakaoMap.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    KakaoMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
