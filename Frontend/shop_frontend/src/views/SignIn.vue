<template>
    <div class="conatiner">
      <div class="row">
        <div class="col-12 justify-content-center d-flex flex-row pt-5">
          <div id="signin" class="flext-item border">
            <h2 class="pt-4">Sign In</h2>
            <form @submit="signin" class="form-group pt-4 pl-4 pr-4">
              <div class="form-group">
                <label>Email </label>
                <input v-model="email" type="email" class="form-control" />
              </div>
              <div class="form-group">
                <label>Password </label>
                <input v-model="password" type="password" class="form-control" />
              </div>
              <button class="btn btn-primary mt-2 py-0">Sign In</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
 <script>
 import axios from "axios";
 import swal from "sweetalert";
 
 export default {
   props: ["baseURL"],
   data() {
     return {
       email: null,
       password: null,
     };
   },
   methods: {
     async signin(e) {
       e.preventDefault();
       const body = {
         email: this.email,
         password: this.password,
       };
 
       try {
         const response = await axios.post(`${this.baseURL}user/signin`, body);
         
         // Check if the response contains an error message
         if (response.data.error) {
           swal({
             text: response.data.error,
             icon: "error",
           });
         } else {
           this.$router.replace("/");
           swal({
             text: "Login successful",
             icon: "success",
           });
         }
       } catch (err) {
         console.error("Error during login:", err);
         // Handle other errors (e.g., network issues)
         swal({
           text: "Please enter your credentials!",
           icon: "error",
         });
       }
     },
   },
 };
 </script>
  <style scoped>
  .btn-primary {
    background-color: #044288;
    color: rgb(255, 255, 255);
    width: 300px;
    margin: 20px;
    font-size: 40px;
  }
  
  @media screen and (min-width: 992px) {
    #signin {
      width: 40%;
    }
  }
  </style>