<template>
  <div id="home">
 <!--    display categories-->
 <div class="container">
      <div class="row">
        
        <div class="col-12 text-center">
          
          <h2 class="pt-3">
            <router-link :to="{ name: 'ViewCategories' }" style="float: right; margin-right: 10px">
            <button class="btn">Categories</button>
          </router-link> 
          Top Categories</h2>
          
        </div>
        
      </div>
      <div class="row">
        <div v-for="index in this.categorySize" :key="index"
             class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <CategoryBox :category="categories[index-1]" />
        </div>

      </div>
    </div>

  <!--    display top products-->
    <div class="container py-2">
      <div class="row">
        <div class="col-12 text-center">
          <h2 class="pt-3"> Top Products</h2>
        </div>
      </div>

      <!--      display products-->

      <div class="row">
        <div v-for="index in this.productSize" :key="index"
             class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <ProductBox :product="products[index-1]" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryBox from "../components/Category/CategoryBox";
import ProductBox from "../components/ProductBox";
export default {
  name: "HomePage",
  components: {ProductBox, CategoryBox},
  props: ["categories","products"],
  data() {
    return {
      categorySize: 0,
      productSize: 0
    }
  },
  mounted() {
    this.categorySize = Math.min(3, this.categories.length );
    this.productSize = Math.min(8, this.products.length);
  }
};
</script>
<style>

</style>