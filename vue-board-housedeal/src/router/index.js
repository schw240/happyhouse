import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import NewsList from "@/components/news/NewsList.vue";

import CatDog from "@/views/CatDog.vue";
import CatDogList from "@/components/catdog/CatDogList.vue";
import CatDogWrite from "@/components/catdog/CatDogWrite.vue";
import CatDogUpdate from "@/components/catdog/CatDogUpdate.vue";
import CatDogView from "@/components/catdog/CatDogView.vue";

import Member from "@/views/Member.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";

import Board from "@/views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";

import House from "@/views/House.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

const boardAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const writerid = store.getters["boardStore/getUserid"];
  if (checkUserInfo.userid != "admin" && checkUserInfo.userid != writerid) {
    alert("권한이 없습니다");
  } else {
    next();
  }
};

const onlyAdmin = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  if (checkUserInfo.userid != "admin") {
    alert("권한이 없습니다");
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    // redirect: "/",
    children: [
      {
        path: "news",
        name: "NewsList",
        component: NewsList,
      },
    ],
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "singin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "singup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "mypage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
    ],
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/noticelist",
    children: [
      {
        path: "noticelist",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "noticewrite",
        name: "NoticeWrite",
        beforeEnter: onlyAdmin,
        component: NoticeWrite,
      },
      {
        path: "noticedetail/:articleno",
        name: "NoticeView",

        component: NoticeView,
      },
      {
        path: "noticeupdate/:articleno",
        name: "NoticeUpdate",
        beforeEnter: onlyAdmin,
        component: NoticeUpdate,
      },
    ],
  },
  {
    path: "/catdog",
    name: "CatDog",
    component: CatDog,
    redirect: "/catdog/list",
    children: [
      {
        path: "list",
        name: "CatDogList",
        component: CatDogList,
      },
      {
        path: "write",
        name: "CatDogWrite",
        beforeEnter: onlyAuthUser,
        component: CatDogWrite,
      },
      {
        path: "detail/:articleno",
        name: "CatDogView",
        beforeEnter: onlyAuthUser,
        component: CatDogView,
      },
      {
        path: "update/:articleno",
        name: "CatDogUpdate",
        beforeEnter: boardAuthUser,
        component: CatDogUpdate,
      },
    ],
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "BoardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "detail/:articleno",
        name: "BoardView",
        beforeEnter: onlyAuthUser,
        component: BoardView,
      },
      {
        path: "update/:articleno",
        name: "BoardUpdate",
        beforeEnter: boardAuthUser,
        component: BoardUpdate,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
  },

  {
    path: "*",
    redirect: "/",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
