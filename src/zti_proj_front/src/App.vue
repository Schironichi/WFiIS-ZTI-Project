<script setup>
</script>

<template>
    <header>
      <nav class="navbar navbar-expand navbar-dark bg-dark">
        <h2 class="navbar-brand">Biblioteka</h2>
        <div class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/ksiazka_list" class="nav-link">Książki</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/dostawa_list" class="nav-link">Dostawy</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/rezerwacja_list" class="nav-link">Rezerwacje</router-link>
          </li>
        </div>
      </nav>

      <div class="container mt-3">
        <router-view />
      </div>
    </header>
    <br>
    <main>
    </main>
</template>

<style scoped>
header {
  line-height: 1.5;
  width: 100%;
}

</style>
