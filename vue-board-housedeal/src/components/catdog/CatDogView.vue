<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col
        class="text-right"
        v-if="loginid === article.userid || loginid === 'admin'"
      >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} </h3><div><h6>${article.userid}아이디</div><div>${article.regTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import { mapActions, mapState } from "vuex";
import { getArticle, deleteArticle } from "@/api/catdog";
// import ReplyList from "../reply/ReplyList.vue";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  components: {},
  data() {
    return {
      article: {},
      loginid: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    changeDateFormat() {
      return moment(new Date(this.article.regtime)).format(
        "YYYY.MM.DD hh:mm:ss"
      );
    },
  },
  created() {
    this.loginid = this.userInfo.userid;
    console.log("캣독뷰 로그인 아이디 확인", this.loginid);
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
        console.log("캣독 뷰에서 호출하는 article", this.article);
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  comments: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(boardStore, ["setUserid"]),
    // async saveUserid() {
    //   console("1111111111111111");
    //   await this.getUserid(this.article.userid);
    // },
    listArticle() {
      this.$router.push({ name: "CatDogList" });
    },
    async moveModifyArticle() {
      console.log("캣독뷰에서 호출", this.article.articleno);
      this.setUserid(this.article.userid);
      this.$router.replace({
        name: "CatDogUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "CatDogList" });
        });
      }
    },
  },
};
</script>

<style></style>
