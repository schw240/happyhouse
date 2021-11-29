<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-input
        v-model="keyword"
        @keyup.enter="searchApt"
        placeholder="엔터키를 눌러 검색하세요"
      ></b-form-input>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const homemapStore = "homemapStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      keyword: null,
    };
  },
  computed: {
    ...mapState(homemapStore, ["sidos", "guguns"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_KEYWORD();

    // this.CLEAR_KEYWORD();
    this.getSido();
  },
  methods: {
    ...mapActions(homemapStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(homemapStore, [
      "SUBMIT_KEYWORD",
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_KEYWORD",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_KEYWORD();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      this.submitKeyword();
      if (this.gugunCode) this.getHouseList(this.gugunCode);
      this.keyword = null;
    },
    submitKeyword() {
      console.log("입력한 keyword: ", this.keyword);
      this.SUBMIT_KEYWORD(this.keyword);
    },
  },
};
</script>

<style></style>
