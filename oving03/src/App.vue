<script setup lang="ts">
import { RouterView, RouterLink } from 'vue-router'
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Calculator</RouterLink>
      <RouterLink to="/feedback">Feedback</RouterLink>
    </nav>
  </header>
  <RouterView />
</template>

<style lang="scss">
$nav-bg: linear-gradient(270deg, 
  #D3F8E2 0%,
  #E4C1F9 50%,
  #f694c1 100%
);
$nav-hover-bg: rgba(0,0,0,0.3);
$nav-active-bg: rgba(0, 0, 0, 0.5);

main {
  * {
    font-size: xx-large;
  }
}

nav {
  background: $nav-bg;
  display: flex;
  gap: 10px;
  height: 3em;
  
  align-items: center;

  margin-bottom: 20px;

  .router-link-active {
    background: $nav-active-bg;
  }

  a {
    text-transform: uppercase;
    text-decoration: none;
    
    color: white;
    text-align: center;

    width: 10em;
    padding-block: .8em;

    :hover {
      background: $nav-hover-bg;
    }
  }
}
</style>
