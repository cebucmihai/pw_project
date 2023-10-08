<template>
  <div class="adminPanel container">
      <router-link :to="{name : 'AdminProduct'}">
        <button type="button" class="btn btn-primary btn-lg">Admin Products</button>
      </router-link>
      <br />
      <router-link :to="{name : 'Category'}">
        <button type="button" class="btn btn-primary btn-lg">Admin Categories</button>
      </router-link>
  </div>
</template>
<script>
export default {
    name:"AdminPage"

}
</script>
<style>

.adminPanel{
  display : flex;
  flex-direction: column;
  align-items: center;
}
.adminPanel button{
  font-size: 50px;
  width: 500px;
  margin: 80px 0;
}
</style>









