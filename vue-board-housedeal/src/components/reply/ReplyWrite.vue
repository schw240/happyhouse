<template>
  <div>
    <b-form @submit="onSubmit">
      <b-form-group id="content-group" label="" label-for="content">
        <b-form-input
          id="content"
          placeholder="내용 입력..."
          rows="5"
          max-rows="15"
          v-model="reply.content"
        ></b-form-input>
        <input
          type="hidden"
          name="userid"
          id="userid"
          v-model="userInfo.userid"
        />
        <input type="hidden" name="boardid" id="boardid" :value="articleno" />
      </b-form-group>
      <div class="replybtn">
        <b-button type="submit" variant="primary" class="m-1">등록</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { writeReply } from "@/api/reply";

const memberStore = "memberStore";
export default {
  name: "ReplyWrite",
  data() {
    return {
      type: "register",
      reply: {
        content: "",
      },
    };
  },
  props: {
    articleno: Number,
  },
  create() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log(this.userInfo.username);
      // let err = true;
      // let msg = "";
      this.registReply();
    },
    registReply() {
      writeReply(
        {
          userid: this.userInfo.userid,
          username: this.userInfo.username,
          content: this.reply.content,
          boardid: this.articleno,
        },
        ({ data }) => {
          let msg = "댓글 등록 실패!!!!";
          if (data === "success") {
            msg = "댓글 등록 완료!!!";
            this.reply.content = "";
          }
          alert(msg);
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
.replybtn {
  text-align: right;
}
</style>
