<template>
  <!-- Wyświetlanie komunikatu o błędzie -->
  <div v-if="errorMessage" class="alert alert-danger">
    {{ errorMessage }}
    <br>
  </div>
  <form @submit.prevent="submitForm" class="my-4">
    <!-- Pola formularza -->
    <div class="form-group">
      <label for="data">Data:</label>
      <input type="date" id="data" v-model="dostawaData.data" required class="form-control" />
    </div>
    <br>
    <div>
      <!-- Przycisk "Modyfikuj" lub "Zapisz" w zależności od wartości zmiennej editMode -->
      <button type="submit" class="btn btn-primary">{{ editMode ? 'Modyfikuj' : 'Zapisz' }}</button>
      <!-- Przycisk "Wróć" przekierowujący do ścieżki /dostawa_list -->
      <router-link to="/dostawa_list" class="btn btn-secondary">Wróć</router-link>
    </div>
  </form>

</template>

<script>
import http from "../http-common"
export default {

  data() {
    return {
      editMode: false,
      dostawaData: {},
      errorMessage: '',
    };
  },

  created() {
    // Sprawdzanie czy tryb edycji jest włączony na podstawie parametru w adresie URL
    this.editMode = !!this.$route.params.id_dostawa;

    if (this.editMode) {
      // Pobieranie danych dostawy w trybie edycji
      this.fetchDostawaData(this.$route.params.id_dostawa)
    }
  },

  methods: {
    fetchDostawaData(id_dostawa) {
      // Wywołanie żądania GET do API w celu pobrania danych dostawy
      http.get(`dostawa/${id_dostawa}`)
          .then(response => {
            this.dostawaData = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },

    submitForm() {
      if (this.editMode) {
        // Aktualizacja danych dostawy w trybie edycji
        this.updateDostawa();
      } else {
        // Dodawanie nowej dostawy
        this.addDostawa();
      }
    },

    addDostawa() {
      // Wywołanie żądania POST do API w celu dodania nowej dostawy
      http.post('dostawa', this.dostawaData)
          .then(response => {
            this.$router.push(`/dostawa_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },

    updateDostawa() {
      // Wywołanie żądania PUT do API w celu aktualizacji danych dostawy
      http.put(`dostawa`, this.dostawaData)
          .then(response => {
            this.$router.push(`/dostawa_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
  },
};
</script>
