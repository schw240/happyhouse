<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-table-simple>
      <b-tr>
        <b-td>번호</b-td>
        <b-td>이름</b-td>
        <b-td>법정동</b-td>
        <b-td>가격</b-td>
      </b-tr>
      <house-list-row
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      />
    </b-table-simple>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  created() {
    this.CLEAR_HOUSE_LIST();
    console.log(this.houses);
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapMutations(houseStore, ["SET_HOUSE_LIST", "CLEAR_HOUSE_LIST"]),
  },
};
</script>

<style></style>
