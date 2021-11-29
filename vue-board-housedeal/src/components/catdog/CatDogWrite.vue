<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <cat-dog-write-form type="register" />
  </b-container>
</template>

<script>
import CatDogWriteForm from "./child/CatDogWriteForm.vue";

export default {
  name: "CatDogWrite",
  components: {
    CatDogWriteForm,
  },
};
</script>

<style></style>
