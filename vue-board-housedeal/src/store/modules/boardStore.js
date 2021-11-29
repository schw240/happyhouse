const boardStore = {
  namespaced: true,
  state: {
    userid: "",
  },
  getters: {
    getUserid: function (state) {
      return state.userid;
    },
  },
  mutations: {
    SET_USERID: (state, userid) => {
      state.userid = userid;
    },
  },
  actions: {
    setUserid({ commit }, userid) {
      commit("SET_USERID", userid);
    },
  },
};

export default boardStore;
