<template>
  <b-tr>
    <b-td>{{ company }}</b-td>
    <b-th class="text-left">
      <!-- <router-link :to="{ name: 'NewsView', params: { title: title } }">{{
        title
      }}</router-link> -->
      <a :href="link" target="_blank">{{ title }}</a>
    </b-th>
    <b-td>{{ upload_date }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "NewsListRow",
  props: {
    title: String,
    link: String,
    company: String,
    upload_date: String,
    content: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
