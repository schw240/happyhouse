<template>
  <div>
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
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt2"
        ></b-form-select>
      </b-col>
      <b-col>
        <b-button @click.prevent="addInterArea" variant="warning"
          >관심등록</b-button
        >
      </b-col>
    </b-row>
    <b-row
      class="mt-4 mb-4"
      v-if="change && tempareas && tempareas.length != 0"
    >
      <b-col class="sm-6">
        <b>관심지역 보기</b>
      </b-col>

      <b-col> </b-col>
      <b-col> </b-col>
      <b-col> </b-col>
    </b-row>
    <b-row
      ><member-my-page-interest-row
        v-for="(area, index) in tempareas"
        :key="index"
        :area="area"
        isin="apt"
      />
    </b-row>
  </div>
</template>

<script>
import MemberMyPageInterestRow from "@/components/user/MemberMyPageInterestRow.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import {
  updateInterestArea,
  insertInterestAreaById,
} from "../../api/member.js";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseSearchBar",
  components: {
    MemberMyPageInterestRow,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      tempareas: [],
      change: true,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    ...mapState(memberStore, ["userInfo", "userInterestArea"]),

    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  updated() {
    this.tempareas = this.userInterestArea;
    this.change = true;
  },
  methods: {
    ...mapActions(memberStore, [
      "getUserInfo",
      "getInterestArea",
      "insertInterestArea",
    ]),
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getDBHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    ...mapMutations(memberStore, ["ADD_AREA_INTERESTAREA"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      console.log("시도코드", this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log("구군코드", this.gugunCode);
      // this.$store.commi("CLEAR_GUGUN_LIST");
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      console.log("구군코드", this.gugunCode);
      // if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
    searchApt2() {
      console.log("동코드", this.dongCode);
      if (this.dongCode) this.getDBHouseList(this.dongCode);
    },
    //관심지역 등록 or 업데이트
    async registInterest() {
      if (this.userInfo == null) {
        alert("로그인이 필요합니다.");
        this.$router.push({ name: "SignIn" });
      } else {
        let param = {
          userid: this.userInfo.userid,
          dongcode: this.dongCode,
        };
        if (this.dongCode) {
          console.log(param);
          await updateInterestArea(param);
          //등록된 관심지역정보를 다시 vuex에 업데이트
          let token = sessionStorage.getItem("access-token");
          await this.getUserInfo(token);
          console.log("등록gn : " + this.userInfo.interestarea);
        } else {
          alert("동까지 선택하세요");
        }
      }
    },
    async addInterArea() {
      if (!this.userInfo) {
        alert("로그인이 필요합니다.");
        return;
      }
      if (this.dongCode && !this.userInterestArea.includes(this.dongCode)) {
        console.log(this.dongCode);
        this.ADD_AREA_INTERESTAREA(this.dongCode);
        await insertInterestAreaById({
          userid: this.userInfo.userid,
          area: this.dongCode,
        });
        await this.getInterestArea(this.userInfo.userid);
        this.change = false;
      } else {
        alert("제대로선택");
      }
    },

    // showInterest() {
    //   //dongcode를 userinfo에서 등록하고
    //   if (this.userInfo == null) {
    //     alert("로그인이 필요합니다.");
    //     this.$router.push({ name: "SignIn" });
    //   } else {
    //     const interCode = this.userInfo.interestarea;
    //     this.sidoCode = interCode.substring(0, 2);
    //     this.gugunCode = interCode.substring(0, 5);
    //     this.dongCode = interCode;
    //     this.getGugun(this.sidoCode);
    //     this.getDong(this.gugunCode);
    //     console.log("시코 : " + this.sidoCode);
    //     console.log("군코 : " + this.gugunCode);
    //     console.log("동코 : " + this.dongCode);
    //     console.log("관코 : " + interCode);
    //     this.getDBHouseList(this.userInfo.interestarea);
    //   }
    // },
  },
};
</script>

<style></style>
