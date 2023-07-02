<template>
  <div v-if="errorMessage" class="alert alert-danger">
    {{ errorMessage }}
    <br>
  </div>
  <form @submit.prevent="submitForm" class="my-4">
    <div class="form-group">
      <label for="tytul">Tytuł:</label>
      <input type="text" id="tytul" v-model="bookData.tytul" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="autor">Autor:</label>
      <input type="text" id="autor" v-model="bookData.autor" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="gatunek">Gatunek:</label>
      <input type="text" id="gatunek" v-model="bookData.gatunek" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="opis">Opis:</label>
      <input type="text" id="opis" v-model="bookData.opis" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="wydawnictwo">Wydawnictwo:</label>
      <input type="text" id="wydawnictwo" v-model="bookData.wydawnictwo" required class="form-control" />
    </div>
    <div class="form-group">
      <label for="rok_wydania">Rok Wydania:</label>
      <input type="number" id="rok_wydania" v-model="bookData.rok_wydania" required class="form-control" />
    </div>
    <br>
    <div>
      <button type="submit" class="btn btn-primary">{{ editMode ? 'Modyfikuj' : 'Zapisz' }}</button>
      <router-link to="/ksiazka_list" class="btn btn-secondary">Wróć</router-link>
    </div>
  </form>

</template>

<script>
import http from "../http-common"
export default {

  data() {
    return {
      editMode: false,
      bookData: {},
      errorMessage: '',
    };
  },

  created() {
    this.editMode = !!this.$route.params.id;
    if (this.editMode) {
      this.fetchBookData(this.$route.params.id)

    }
  },

  methods: {
    fetchBookData(bookId) {
      http.get(`ksiazka/${bookId}`)
          .then(response => {
            this.bookData = response.data;
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    submitForm() {
      if (this.editMode) {
        this.updateBook();
      } else {
        this.addBook();
      }
    },
    addBook() {
      http.post('ksiazka', this.bookData)
          .then(response => {
            this.$router.push(`/ksiazka_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    updateBook() {
      http.put(`ksiazka`, this.bookData)
          .then(response => {
            this.$router.push(`/ksiazka_list`);
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
  },
};
</script>
