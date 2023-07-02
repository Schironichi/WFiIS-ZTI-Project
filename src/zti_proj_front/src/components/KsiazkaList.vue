<script>
import http from "../http-common"

export default {
  name: 'ksiazka-list',
  data() {
    return {
      columns: [
        { field: 'tytul', label: 'Tytuł' },
        { field: 'autor', label: 'Autor' },
        { field: 'gatunek', label: 'Gatunek' },
        { field: 'opis', label: 'Opis' },
        { field: 'wydawnictwo', label: 'Wydawnictwo' },
        { field: 'rok_wydania', label: 'Rok Wydania', type: 'number' },
        { field: 'dostepne', label: 'Dostępne książki', type: 'number', formatter: 'formatValue' },
        { field: 'akcje', label: 'Akcje', type: 'edit' },
      ],
      jsonData: [],
      errorMessage: '',
    };
  },
  async created() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await http.get('ksiazka');
        this.jsonData = response.data;
      } catch (error) {
        console.error(error);
        this.errorMessage = `Wystąpił błąd: ${error}`;
      }
    },
    editRow(row) {
      this.$router.push(`/ksiazka_edit/${row}`);
    },
    delRow(row) {
      http.delete(`ksiazka/${row}`)
          .then(response => {
            this.fetchData();
          })
          .catch(error => {
            console.error(error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    formatValue(value) {
      if (value < 0) {
        return 'red-text';
      }
      return '';
    },
  },
};
</script>

<template>
  <div>
    <div v-if="errorMessage" class="alert alert-danger">
      {{ errorMessage }}
      <br>
    </div>
    <router-link to="/ksiazka_edit" class="btn btn-primary">Nowa Książka</router-link>
    <br><br>
    <div class="table-container">
      <vue-good-table class="custom-table"
          :columns="columns"
          :rows="jsonData"
          :cell-class="{'red-text': true}">
        <template #table-row="props">
          <span v-if="props.column.field == 'akcje'">
            <button class="btn btn-secondary" @click="editRow(props.row.id_ksiazka)">Edytuj</button>
            <button class="btn btn-danger" @click="delRow(props.row.id_ksiazka)">Usuń</button>
          </span>
        </template>
      </vue-good-table>
    </div>
  </div>
</template>

<style scoped>
.red-text {
  color: red;
}
</style>