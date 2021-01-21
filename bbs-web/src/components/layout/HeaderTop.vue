<template>
    <div class="header-top">
      <div class="header__logo">
        <img class="logo-img" src="@/assets/images/logo.jpg"/>
      </div>
      <div class="header__userprofile">
        <span class="header__userprofile__nickname">닉네임</span>
      </div>
    </div>

</template>

<script>
export default {
  name: 'HeaderTop',
};
</script>

<style lang="scss" scoped>

.header-top {
  display: grid;
  grid-column: 1 / 4;
  grid-template-columns: 1fr 1fr;
  background-color: antiquewhite;

  .logo-img {
    width: 100px;
  }

  .header__userprofile {
    text-align: right;
    padding: 0 10px;
  }
}

</style>
