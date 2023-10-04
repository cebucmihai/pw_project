
<template>
    <div>Add Category</div>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3>Add Category</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form>
                    <div class="form-group">
                        <label>Category Name</label>
                        <input type="text" class="form-control" v-model="categoryName"/>
                    </div>
                </form>
                <form>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea type="text" class="form-control" v-model="description" />
                    </div>
                </form>
                <form>
                    <div class="form-group">
                        <label>Image</label>
                        <input type="text" class="form-control" v-model="imageUrl"/>
                    </div>
                </form>
                <button type="button" class="btn btn-primary" @click="addCategory">Submit</button>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
    
</template>
<script>
const axios = require("axios");
const sweetalert = require("sweetalert");
export default {
  data() {
    return {
         categoryName: '',
         description: '',
         imageUrl: '',
    };
  },
  methods: {
    addCategory() {
      const newCategory = {
        categoryName: this.categoryName,
        description: this.description,
        imageUrl: this.imageUrl,
      };
      const baseURL = "http://localhost:8080";

      axios({
        method: "post",
        url: `${baseURL}/category/create`,
        data: JSON.stringify(newCategory),
        headers: {
          "Content-Type":"application/json"
        }
      })
        .then(()=>{
          sweetalert({
            text: 'Category added succesfully',
            icon: "success",
          });
        })
        .catch((err)=>{
          console.log(err);
        });
    }
  }
}
</script>
<style scoped></style>