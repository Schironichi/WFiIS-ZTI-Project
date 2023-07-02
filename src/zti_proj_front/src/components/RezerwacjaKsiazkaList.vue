<script>
import http from "../http-common"

export default {
  name: 'rezerwacja-ksiazka-list',
  data() {
    return {
      columns: [
        { field: 'tytul', label: 'Tytuł', },
        { field: 'czas_wypozyczenia', label: 'Czas wypożyczenia', },
        { field: 'rezerwacja_ilosc', label: 'Ilość', },
        { field: 'akcje', label: 'Akcje', type: 'edit' },
      ],
      jsonData: [],
      rezerwacjaKsiazkaData: [],
      errorMessage: '',
    };
  },
  async created() {
    await this.fetchData();
      },
  methods: {
    async fetchData() {
      try {
        const response = await http.get(`rezerwacja-ksiazka/${this.$route.params.id_rezerwacja}`);
        this.jsonData = response.data;
      } catch (error) {
        console.error(error);
        this.errorMessage = `Wystąpił błąd: ${error}`;
      }
    },
    editRow(row) {
      this.$router.push(`/rezerwacja_ksiazka_edit/${this.$route.params.id_rezerwacja}/${row}`);
    },
    delRow(row) {
      http.delete(`rezerwacja-ksiazka/${this.$route.params.id_rezerwacja}/${row}`)
          .then(response => {
            this.fetchData();
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    getAddLink() {
      return `/rezerwacja_ksiazka_edit/${this.$route.params.id_rezerwacja}`;
    }
  },
};
</script>

<template>
  <div>
    <div v-if="errorMessage" class="alert alert-danger">
      {{ errorMessage }}
      <br>
    </div>
    <router-link :to="getAddLink()" class="btn btn-primary">Dodaj Książkę</router-link>
    <router-link to="/rezerwacja_list" class="btn btn-secondary">Wróć</router-link>
    <br><br>
    <vue-good-table class="custom-table"
        :columns="columns"
        :rows="jsonData">
      <template #table-row="props">
        <span v-if="props.column.field == 'akcje'">
          <button class="btn btn-primary" @click="editRow(props.row.id_ksiazka)">Edytuj</button>
          <button class="btn btn-danger" @click="delRow(props.row.id_ksiazka)">Usuń</button>
        </span>
      </template>
    </vue-good-table>
  </div>
</template>