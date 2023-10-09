<template>
  <div class="container">
    <div class="row pt-5">
      <div class="col-md-1"></div>
      <!-- display image -->
      <div class="col-md-4 col-12">
        <img :src="product.imageURL" class="img-fluid" />
      </div>
      <!-- display product details -->
      <div class="col-md-6 col-12 pt-3 pt-md-0">
        <h4>{{ product.name }}</h4>
        <h6 class="catgory font-italic">{{ category.categoryName }}</h6>
        <h6 class="font-weight-bold">$ {{ product.price }}</h6>
        <p>
          {{ product.description }}
        </p>
        <div class="d-flex flex-row justify-content-between">
          <div class="input-group col-md-3 col-4 p-0">
            <div class="input-group-prepend">
              <span class="input-group-text">Quantity</span>
            </div>
            <input type="number" class="form-control" v-model="quantity" />
          </div>
          <button
            id="wishlist-button"
            class="btn mr-3 p-2 py-0"
            @click="toggleWishlist"
          >
            {{ wishListString }}
          </button>
          <div class="input-group col-md-3 col-4 p-0">
            <button class="btn" id="add-to-cart-button" @click="addToCart">
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import swal from "sweetalert";
import axios from "axios";

export default {
  data() {
    return {
      product: {},
      category: {},
      quantity: 1,
      wishListString: "Add to wishlist",
      isInWishlist: false,
    };
  },
  props: ["baseURL", "products", "categories"],
  methods: {
    addToWishlist() {
      if (!this.token) {
        swal({
          text: "Please LogIn to add a product in wishlist!",
          icon: "error",
        });
        return;
      }

      axios.get(`${this.baseURL}wishlist/${this.token}`)
        .then(response => {
          const wishlistProducts = response.data;
          this.isInWishlist = wishlistProducts.some(item => item.id === this.product.id);
          if (this.isInWishlist) {
            swal({
              text: "Product is already in your wishlist!",
              icon: "warning",
            });
          } else {
            axios.post(`${this.baseURL}wishlist/add?token=${this.token}`, {
              id: this.product.id,
            })
            .then(() => {
              this.isInWishlist = true;
              this.wishListString = "Remove from Wishlist";
              swal({
                text: "Added to Wishlist",
                icon: "success",
              });
            })
            .catch((err) => {
              console.log("err", err);
            });
          }
        })
        .catch(err => {
          console.log("err", err);
        });
    },
    removeFromWishlist() {
      if (!this.token) {
        swal({
          text: "Please LogIn to remove a product from wishlist!",
          icon: "error",
        });
        return;
      }

      axios.delete(`${this.baseURL}wishlist/delete/${this.product.id}/token=${this.token}`)
        .then(() => {
          this.isInWishlist = false;
          this.wishListString = "Add to Wishlist";
          swal({
            text: "Removed from Wishlist",
            icon: "success",
          });
        })
        .catch((err) => {
          console.log("err", err);
        });
    },
    addToCart() {
    if (!this.token) {
      swal({
        text: "Please LogIn to add item to cart",
        icon: "error",
      });
      return;
    }

    axios
      .post(`${this.baseURL}cart/add?token=${this.token}`, {
        productId: this.id,
        quantity: this.quantity,
      })
      .then((res) => {
        if (res.status == 201) {
         
          swal({
            text: "Product added to cart",
            icon: "success",
          });


        }
      })
      .catch((err) => console.log("err", err));
  },
    toggleWishlist() {
      if (this.isInWishlist) {
        this.removeFromWishlist();
      } else {
        this.addToWishlist();
      }
    },
    fetchWishlist() {
      axios.get(`${this.baseURL}wishlist/${this.token}`)
        .then(response => {
          const wishlistProducts = response.data;
          this.isInWishlist = wishlistProducts.some(item => item.id === this.product.id);
          this.wishListString = this.isInWishlist ? "Remove from Wishlist" : "Add to Wishlist";
        })
        .catch(err => {
          console.log("err", err);
        });
    },
  },
  
  mounted() {
    this.id = this.$route.params.id;
    this.product = this.products.find((product) => product.id == this.id);
    this.category = this.categories.find(
      (category) => category.id == this.product.categoryId
    );
    this.token = localStorage.getItem("token");

    // Check if the product is in the wishlist when the component is mounted
    if (this.token) {
      this.fetchWishlist();
    }
  },
};
</script>

<style>
.category {
  font-weight: 400;
}

#wishlist-button {
  background-color: #b9b9b9;
}

#add-to-cart-button {
  background-color: #1e6901;
}
</style>
