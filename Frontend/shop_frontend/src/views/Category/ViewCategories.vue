<template>
     <!--    display categories-->
 <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h2 class="pt-3">
         All Categories</h2>
        </div>
      </div>
      <div class="row">
        <div v-for="index in this.categorySize" :key="index"
             class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <CategoryBox :category="categories[index-1]" />
        </div>
      </div>
    </div>
</template>

<script>
    import CategoryBox from "../../components/Category/CategoryBox"; // Update import statement
    export default {
  name: "ViewCategories",
  components: {CategoryBox},
  props: ["categories"],
  data() {
    return {
      categorySize: 0,
    }
  },
  mounted() {
    this.categorySize = this.categories.length;
  }
};
</script>
