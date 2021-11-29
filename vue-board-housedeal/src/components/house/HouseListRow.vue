<template>
  <b-tr
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-td>{{ house.aptCode }}</b-td>
    <b-td>{{ house.aptName }}</b-td>
    <b-td>{{ house.dongName }}</b-td>
    <b-td
      >{{
        (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
      }}원</b-td
    >
    <!-- <b-col cols="2" class="text-center align-self-center">
        <b-img
          thumbnail
          src="https://picsum.photos/250/250/?image=58"
          alt="Image 1"
        ></b-img>
      </b-col>
      <b-col cols="10" class="align-self-center">
        [{{ house.dongCode }}] {{ house.aptName }}
      </b-col> -->
  </b-tr>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "detailMap"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.detailMap(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
