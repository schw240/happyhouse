<template>
  <!-- <b-tr> -->
  <!-- <b-td>{{ articleno }}</b-td> -->
  <!-- <b-th class="text-left">
      <router-link
        :to="{ name: 'CatDogView', params: { articleno: articleno } }"
        >{{ saveFile }}</router-link
      >
    </b-th> -->
  <!-- <b-td>{{ saveFile }}</b-td> -->
  <!-- </b-tr> -->
  <router-link :to="{ name: 'CatDogView', params: { articleno: articleno } }">
    <b-img
      v-if="fileInfos"
      thumbnail
      fluid
      id="listrowImg"
      :src="
        require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${fileInfos[0].saveFolder}/${fileInfos[0].saveFile}`)
      "
    />
  </router-link>
</template>

<script>
// import moment from "moment";

export default {
  name: "CatDogListRow",
  data() {
    return {
      fileurl: this.saveFile,
      arno: null,
    };
  },
  mounted() {
    console.log("리스트 로우에서 호출하는 articleno: ", this.articleno);
    console.log("fileInfos: ", this.fileInfos);
  },
  created() {},
  props: {
    articleno: Number,
    subject: String,
    index: Number,
    //content: String,

    fileInfos: {
      saveFolder: String,
      saveFile: String,
      originFile: String,
    },
  },
  // computed: {
  //   changeDateFormat() {
  //     return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
  // methods: {
  //   resolve_img_url: function (path) {
  //     let images = require.context("../assets/", false, /\.png$|\.jpg$/);
  //     return images("./" + path);
  //   },
  // },
};
</script>

<style>
#imgContainer {
  width: 300px;
}
#listrowImg {
  width: 300px;
  height: 300px;
}
</style>
