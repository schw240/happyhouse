<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="article.userid"
            type="text"
            required
            placeholder="작성자 입력..."
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="content-group fileInput"
          class="dragdrop col-sm-3 float-right"
        >
          <b-form-file
            multiple
            v-model="images"
            accept="image/jpeg, image/jpg, image/png, image/gif"
            placeholder="사진을 선택하세요"
            style="margin-left: 13px"
          />
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/catdog.js";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "CatDogWriteForm",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      images: [],
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.article.userid = this.userInfo.userid;
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.updateArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      //this.article.images = [];
      this.$router.push({ name: "CatDogList" });
    },

    registArticle() {
      console.log(this.images);
      var formData = new FormData();
      formData.append("userid", this.article.userid);
      formData.append("subject", this.article.subject);
      formData.append("content", this.article.content);
      for (let i = 0; i < this.images.length; i++) {
        formData.append("files", this.images[i]);
        console.log(this.images[i]);
      }
      // let param = {
      //   userid: this.article.userid,
      //   subject: this.article.subject,
      //   content: this.article.content,
      //   images: this.images,
      // };
      console.log(formData);
      writeArticle(
        formData,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateArticle() {
      var formData = new FormData();
      formData.append("userid", this.article.userid);
      formData.append("subject", this.article.subject);
      formData.append("content", this.article.content);
      for (let i = 0; i < this.images.length; i++) {
        formData.append("files", this.images[i]);
        console.log(this.images[i]);
      }
      modifyArticle(
        formData,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "CatDogList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "CatDogList" });
    },
  },
};
</script>

<style>
#fileInput .custom-file {
  height: 25px;
}
#fileInput .custom-file-input {
  height: 25px;
}
#fileInput {
  height: 25px;
  margin: 0;
  margin-top: -25px;
}
#fileInput .custom-file-label {
  width: 82x;
  height: 25px;
  line-height: 12px;
  border: 0px;
  opacity: 0;
  overflow: hidden;
}
#fileInput .custom-file:hover .custom-file-label {
  border: 0px;
  cursor: pointer;
}
#fileInput .custom-file-label::before {
  visibility: hidden;
}
</style>
