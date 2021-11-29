<template>
  <b-container style="text-align: left">
    <b-row>
      <b-col>
        {{ codeToString }}
      </b-col>
      <b-col v-if="this.isin != 'mypage'">
        <b-button
          size="sm"
          variant="light"
          v-if="this.isin == 'apt'"
          @click.prevent="showInterest"
          @mouseover="colorChange(true)"
          @mouseout="colorChange(false)"
          :calss="{ 'mouse-over-pagecolor': isColor }"
        >
          보기
        </b-button>
        <b-button
          size="sm"
          variant="warning"
          v-if="this.isin == 'mod'"
          @click.prevent="deleteInterest"
          @mouseover="colorChange(true)"
          @mouseout="colorChange(false)"
          :calss="{ 'mouse-over-pagecolor': isColor }"
        >
          삭제
        </b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { convertCodeToString } from "../../api/member.js";
import { mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "MemberMyPagInterestRow",
  data() {
    return {
      codeToString: "",
      isColor: false,
    };
  },
  props: {
    area: String,
    isin: String,
  },
  created() {
    this.convertInterCodeToString();
  },
  methods: {
    ...mapActions(houseStore, ["getDBHouseList"]),
    ...mapMutations(memberStore, ["DELETE_AREA"]),
    //동코드를 글자로
    convertInterCodeToString() {
      //console.log(this.area);
      convertCodeToString(this.area, (response) => {
        this.codeToString = response.data;
      });
    },
    showInterest() {
      console.log("현재", this.area);
      this.getDBHouseList(this.area);
    },
    deleteInterest() {
      this.DELETE_AREA(this.area);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
