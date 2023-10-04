<template>
    <router-link :to="{name: 'Category'}" >
        <button class="btn btn-primary btn-lg"> Admin Category</button>
    </router-link>
    <br />
    <router-link :to="{name: 'AdminProduct'}" >
        <button class="btn btn-primary btn-lg"> Admin Product</button>
    </router-link>


</template>
<script>
export default {
  name: 'AdminPage', 
}
</script>
<style>
    .btn {
        margin-bottom: 20px;
    }
</style>