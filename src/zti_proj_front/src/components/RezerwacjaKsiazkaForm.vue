<template>
  <div v-if="errorMessage" class="alert alert-danger">
    {{ errorMessage }}
    <br>
  </div>
  <form @submit.prevent="submitForm" class="my-4">
    <div class="form-group">
      <label for="czas_wypozyczenia">Czas wypożyczenia:</label>
      <input type="number" id="czas_wypozyczenia" v-model="rezerwacjaKsiazkaData.czas_wypozyczenia" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="rezerwacja_ilosc">Ilość:</label>
      <input type="number" id="rezerwacja_ilosc" v-model="rezerwacjaKsiazkaData.rezerwacja_ilosc" required class="form-control" />
    </div>
    <div class="form-group" v-if="!this.$route.params.id_ksiazka">
      <label for="book">Wybierz Książkę:</label>
      <select id="ksiazka" v-model="wybranaKsiazka" class="form-control">
        <option value="">wybierz książkę</option>
        <option v-for="ksiazka in dostepneKsiazki" :key="ksiazka.id_ksiazka" :value="ksiazka.id_ksiazka">{{ ksiazka.tytul }}</option>
      </select>
    </div>
    <br>
    <div>
      <button type="submit" class="btn btn-primary">{{ editMode ? 'Modyfikuj' : 'Zapisz' }}</button>
      <router-link :to="'/rezerwacja_ksiazka_list/'+this.$route.params.id_rezerwacja" class="btn btn-secondary">Wróć</router-link>
    </div>
  </form>

</template>

<script>
import http from "../http-common"
export default {

  data() {
    return {
      editMode: false,
      rezerwacjaKsiazkaData: {},
      errorMessage: '',
      dostepneKsiazki: [],
      wybranaKsiazka: '',
    };
  },

  created() {
    this.editMode = !!this.$route.params.id_ksiazka;
    if (this.editMode) {
      this.fetchRezerwacjaKsiazkaData(this.$route.params.id_rezerwacja, this.$route.params.id_ksiazka)
    } else {
      this.fetchKsiazkaData();
    }
  },

  watch: {
    wybranaKsiazka: function(newKsiazkaId) {
      const selectedKsiazka = this.dostepneKsiazki.find(ksiazka => ksiazka.id_ksiazka === newKsiazkaId);
      if (selectedKsiazka) {
        this.rezerwacjaKsiazkaData.rezerwacja_ilosc = selectedKsiazka.dostepne;
      }
    }
  },

  methods: {
    fetchRezerwacjaKsiazkaData(id_rezerwacja, id_ksiazka) {
      http.get(`rezerwacja-ksiazka/${id_rezerwacja}/${id_ksiazka}`)
          .then(response => {
            this.rezerwacjaKsiazkaData = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    fetchKsiazkaData() {
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
        this.updateRezerwacjaKsiazka();
      } else {
        this.addRezerwacjaKsiazka();
      }
    },
    addRezerwacjaKsiazka() {
      this.rezerwacjaKsiazkaData.id_rezerwacja = parseInt(this.$route.params.id_rezerwacja);
      this.rezerwacjaKsiazkaData.id_ksiazka = this.wybranaKsiazka;
      http.post('rezerwacja-ksiazka', this.rezerwacjaKsiazkaData)
          .then(response => {
            this.$router.push(`/rezerwacja_ksiazka_list/${this.$route.params.id_rezerwacja}`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    updateRezerwacjaKsiazka() {
      http.put(`rezerwacja-ksiazka`, this.rezerwacjaKsiazkaData)
          .then(response => {
            this.$router.push(`/rezerwacja_ksiazka_list/${this.$route.params.id_rezerwacja}`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
  },
};
</script>
