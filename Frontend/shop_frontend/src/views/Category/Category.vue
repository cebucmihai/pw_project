<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Our Categories</h3>
        <router-link :to="{ name: 'AddCategory' }">
          <button class="btn" style="float: right">Add Category</button>
        </router-link>
        <!-- Back button to redirect to http://localhost:8081/admin -->
        <router-link :to="{ name: 'Admin' }" style="float: left; margin-right: 10px">
          <button class="btn">Back</button>
        </router-link>
      </div>
    </div>
    <div class="row">
      <div v-for="category of categories" :key="category.id" class="col-xl-4 col-md-6 col-12 pt-3 d-flex">
        <CategoryBox :category="category"></CategoryBox>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryBox from "../../components/Category/CategoryBox.vue";
export default {
  name: "CategoryView",
  props: ["categories"],
  components: { CategoryBox },
  data() {
    return {
      baseURL: "http://localhost:8080/",
    };
  },
  methods: {},
  mounted() {},
};
</script>

<style scoped></style>
