<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h4 class="pt-3">Edit Product</h4>
        </div>
      </div>
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <form v-if="product">
            <div class="form-group">
              <label> Category</label>
              <select class="form-control" v-model="product.categoryId" required>
                <option v-for="category of categories" :key="category.id" :value="category.id">
                  {{ category.categoryName }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label>Name</label>
              <input type="text" class="form-control" v-model="product.name" required />
            </div>
            <div class="form-group">
              <label>Description</label>
              <input type="text" class="form-control" v-model="product.description" required />
            </div>
            <div class="form-group">
              <label>Image URL</label>
              <input type="text" class="form-control" v-model="product.imageURL" required />
            </div>
            <div class="form-group">
              <label>Price</label>
              <input type="number" class="form-control" v-model="product.price" required />
            </div>
            <div class="d-flex justify-content-between">
              <button type="button" class="btn btn-primary btn-lg" @click="editProduct">Submit</button>
              <button type="button" class="btn btn-danger btn-lg" @click="deleteProduct">Delete Product</button>
              <button type="button" class="btn btn-secondary btn-lg" @click="$router.go(-1)">Back</button>
            </div>
          </form>
        </div>
        <div class="col-3"></div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import swal from 'sweetalert';
  
  export default {
    data() {
      return {
        product: null,
        id: null,
      };
    },
    props: ["baseURL", "categories", "products"],
    methods: {
      async editProduct() {
        await axios.post(`${this.baseURL}product/update/${this.id}`, this.product)
          .then(() => {
            this.$emit("fetchData");
            this.$router.push({ name: 'AdminProduct' });
            swal({
              text: "Product has been updated successfully",
              icon: "success",
            });
          })
          .catch((err) => console.log('err', err));
      },
      async deleteProduct() {
        const confirmation = await swal({
          text: "Are you sure you want to delete this product?",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        });
  
        if (confirmation) {
          await axios.delete(`${this.baseURL}product/delete/${this.id}`)
            .then(() => {
              this.redirectToAdminCategory();
            })
            .catch((err) => console.log('err', err));
        }
      },
      redirectToAdminCategory() {
      window.location.href = "http://localhost:8081/admin/product";
    },
    },
    mounted() {
      this.id = this.$route.params.id;
      this.product = this.products.find((product) => product.id == this.id);
    },
  };
  </script>
  