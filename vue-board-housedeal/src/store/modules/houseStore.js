import {
  sidoList,
  gugunList,
  dongList,
  aptList,
  houseList,
} from "@/api/house.js";
const { VUE_APP_APT_DEAL_API_KEY } = require("@/config/index.js");
const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    map: null,
    aroundinfo: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_DB_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_DETAIL_MAP(state, house) {
      state.house = house;
      console.log("set_detail_map에서 호출한 house : ", house);
      // house 정보를 불러오므로 이제 선택한 아파트를 map에다가 그려주기
    },
    SET_AROUND_INFO(state, data) {
      state.aroundinfo.push(data);
    },
  },

  actions: {
    getKeyword: ({ commit }, keyword) => {
      commit("SET_KEYWORD", keyword);
    },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log("store에서 호출하는 sido", data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          console.log("store에서 호출하는 구군", commit, data);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          console.log("store에서 dong 출력", data);
          console.log(commit, data);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, gugunCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = VUE_APP_APT_DEAL_API_KEY;

      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          // console.log("houseStore에서 호출하는 houseList api", response);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDBHouseList: ({ commit }, dongCode) => {
      const params = { dong: dongCode };
      aptList(
        params,
        (response) => {
          console.log("store에서 apt 출력", response);
          console.log(commit, response);
          commit("SET_DB_HOUSE_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    detailMap({ commit }, house) {
      // 나중에 house.일력번호를 이용하여 API 호출
      // 한 채에 대한 정보는 db에서 가져오기

      commit("SET_DETAIL_MAP", house);
    },
  },
};

export default houseStore;
