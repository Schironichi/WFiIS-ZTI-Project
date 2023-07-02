<template>
  <div v-if="errorMessage" class="alert alert-danger">
    {{ errorMessage }}
    <br>
  </div>
  <form @submit.prevent="submitForm" class="my-4">
    <div class="form-group">
      <label for="email">Adres E-mail:</label>
      <input type="email" id="email" v-model="rezerwacjaData.email" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="telefon">Numer Telefonu:</label>
      <input type="tel" id="telefon" v-model="rezerwacjaData.telefon" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="imie">Imię:</label>
      <input type="text" id="imie" v-model="rezerwacjaData.imie" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="data_rezerwacji">Data Rezerwacji:</label>
      <input type="date" id="data_rezerwacji" v-model="rezerwacjaData.data_rezerwacji" required class="form-control" />
    </div>
    <br>
    <div>
      <button type="submit" class="btn btn-primary">{{ editMode ? 'Modyfikuj' : 'Zapisz' }}</button>
      <router-link to="/rezerwacja_list" class="btn btn-secondary">Wróć</router-link>
    </div>
  </form>

</template>

<script>
import http from "../http-common"
export default {

  data() {
    return {
      editMode: false,
      rezerwacjaData: {},
      errorMessage: '',
    };
  },

  created() {
    this.editMode = !!this.$route.params.id_rezerwacja;
    if (this.editMode) {
      this.fetchRezerwacjaData(this.$route.params.id_rezerwacja)
    }
  },

  methods: {
    fetchRezerwacjaData(id_rezerwacja) {
      http.get(`rezerwacja/${id_rezerwacja}`)
          .then(response => {
            this.rezerwacjaData = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    submitForm() {
      if (this.editMode) {
        this.updateRezerwacja();
      } else {
        this.addRezerwacja();
      }
    },
    addRezerwacja() {
      http.post('rezerwacja', this.rezerwacjaData)
          .then(response => {
            this.$router.push(`/rezerwacja_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    updateRezerwacja() {
      http.put(`rezerwacja`, this.rezerwacjaData)
          .then(response => {
            this.$router.push(`/rezerwacja_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
  },
};
</script>
