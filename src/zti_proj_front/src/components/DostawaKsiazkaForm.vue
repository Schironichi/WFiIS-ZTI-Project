<template>
  <!-- Wyświetlanie komunikatu o błędzie -->
  <div v-if="errorMessage" class="alert alert-danger">
    {{ errorMessage }}
    <br>
  </div>
  <form @submit.prevent="submitForm" class="my-4">
    <!-- Pola formularza -->
    <div class="form-group">
      <label for="ilosc">Ilość:</label>
      <input type="number" id="ilosc" v-model="dostawaKsiazkaData.ilosc" required class="form-control" />
    </div>
    <div class="form-group" v-if="!this.$route.params.id_ksiazka">
      <!-- Wybór książki -->
      <label for="book">Wybierz Książkę:</label>
      <select id="ksiazka" v-model="wybranaKsiazka" class="form-control">
        <option value="">wybierz książkę</option>
        <option v-for="ksiazka in dostepneKsiazki" :key="ksiazka.id_ksiazka" :value="ksiazka.id_ksiazka">{{ ksiazka.tytul }}</option>
      </select>
    </div>
    <br>
    <div>
      <!-- Przycisk "Modyfikuj" lub "Zapisz" w zależności od wartości zmiennej editMode -->
      <button type="submit" class="btn btn-primary">{{ editMode ? 'Modyfikuj' : 'Zapisz' }}</button>

      <!-- Przycisk "Wróć" przekierowujący do ścieżki /dostawa_ksiazka_list z odpowiednim parametrem id_dostawa -->
      <router-link :to="'/dostawa_ksiazka_list/'+this.$route.params.id_dostawa" class="btn btn-secondary">Wróć</router-link>
    </div>
  </form>

</template>

<script>
import http from "../http-common"
export default {

  data() {
    return {
      editMode: false,
      dostawaKsiazkaData: {},
      errorMessage: '',
      dostepneKsiazki: [],
      wybranaKsiazka: '',
    };
  },

  created() {
    this.editMode = !!this.$route.params.id_ksiazka;
    if (this.editMode) {
      // Pobieranie danych dostawy książki w trybie edycji
      this.fetchDostawaKsiazkaData(this.$route.params.id_dostawa, this.$route.params.id_ksiazka)
    } else {
      // Pobieranie dostępnych książek
      this.fetchKsiazkaData();
    }
  },

  methods: {
    fetchDostawaKsiazkaData(id_dostawa, id_ksiazka) {
      // Wywołanie żądania GET do API w celu pobrania danych dostawy książki
      http.get(`dostawa-ksiazka/${id_dostawa}/${id_ksiazka}`)
          .then(response => {
            this.dostawaKsiazkaData = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    fetchKsiazkaData() {
      // Wywołanie żądania GET do API w celu pobrania listy książek
      http.get(`ksiazka`)
          .then(response => {
            this.dostepneKsiazki = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    submitForm() {
      if (this.editMode) {
        // Przesłanie formularza aktualizacji
        this.updateDostawaKsiazka();
      } else {
        // Przesłanie formularza dodawania
        this.addDostawaKsiazka();
      }
    },
    addDostawaKsiazka() {
      // Przypisanie id_dostawa i id_ksiazka do danych dostawy książki
      this.dostawaKsiazkaData.id_dostawa = parseInt(this.$route.params.id_dostawa);
      this.dostawaKsiazkaData.id_ksiazka = this.wybranaKsiazka;

      // Wywołanie żądania POST do API w celu dodania nowej dostawy książki
      http.post('dostawa-ksiazka', this.dostawaKsiazkaData)
          .then(response => {
            this.$router.push(`/dostawa_ksiazka_list/${this.$route.params.id_dostawa}`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },

    updateDostawaKsiazka() {
      // Wywołanie żądania PUT do API w celu aktualizacji danych dostawy książki
      http.put(`dostawa-ksiazka`, this.dostawaKsiazkaData)
          .then(response => {
            this.$router.push(`/dostawa_ksiazka_list/${this.$route.params.id_dostawa}`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
  },
};
</script>
