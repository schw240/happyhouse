import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById, getInterestAreaById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    userInterestArea: [],
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_INTERESTAREA: (state, interestarea) => {
      state.userInterestArea = interestarea;
    },
    ADD_AREA_INTERESTAREA: (state, area) => {
      state.userInterestArea.push(area);
    },
    DELETE_AREA: (state, area) => {
      const index = state.userInterestArea.indexOf(area);
      state.userInterestArea.splice(index, 1);
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            alert("로그인 성공");
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      await findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            //console.log(response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getInterestArea({ commit }, userid) {
      // console.log("로그인성공 관심지역불러오기", userid);
      await getInterestAreaById(userid, (response) => {
        //console.log("결과", response);
        if (response.data.message === "success") {
          commit("SET_USER_INTERESTAREA", response.data.areas);
        } else {
          console.log("관심지역 없음");
        }
      });
    },
  },
};

export default memberStore;
