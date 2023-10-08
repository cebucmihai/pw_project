<template>
  <div class="add_category container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Add Category</h3>
      </div>
    </div>
    <div class="row">
      <div class="col-3"></div>
      <div class="col-6">
        <form>
          <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" v-model="categoryName" />
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea type="text" class="form-control" v-model="description" />
          </div>
          <div class="form-group">
            <label>Image</label>
            <input type="text" class="form-control" v-model="imageUrl" />
          </div>
          <button type="button" class="btn btn-primary" @click="addCategory">
            Submit
          </button>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import swal from "sweetalert";

export default {
  data() {
    return {
      categoryName: "",
      description: "",
      imageUrl: "",
    };
  },
  methods: {
    addCategory() {
      console.log(this.categoryName, this.description);
      const newCategory = {
        categoryName: this.categoryName,
        description: this.description,
        imageUrl: this.imageUrl,
      };

      const baseURL = "http://localhost:8080/";

      axios({
        method: "post",
        url: `${baseURL}category/create`,
        data: JSON.stringify(newCategory),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then(() => {
          swal({
            text: "Category added successfully",
            icon: "success",
          }).then(() => {
            this.$router.push({ path: "/admin/category" }); // Redirect to the specified route
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
.add_category button {
  margin: 60px 0;
}
</style>
