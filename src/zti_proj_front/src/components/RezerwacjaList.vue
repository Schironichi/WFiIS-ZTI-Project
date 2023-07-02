<script>
import http from "../http-common"

export default {
  name: 'rezerwacja-list',
  data() {
    return {
      columns: [
        { field: 'id_rezerwacja', label: 'ID' },
        { field: 'data_rezerwacji',
          label: 'Data Rezerwacji',
          type: 'date',
          dateInputFormat: 'yyyy-MM-dd',
          dateOutputFormat: 'dd-MM-yyyy',},
        { field: 'wypozyczenie', label: 'Najkrótsze wypożyczenie (dni)'},
        { field: 'email', label: 'E-mail' },
        { field: 'telefon', label: 'Numer Telefonu' },
        { field: 'imie', label: 'Imię' },
        { field: 'akcje', label: 'Akcje', type: 'edit' },
      ],
      jsonData: [],
      lendData: [],
      errorMessage: '',
    };
  },
  async created() {
    await this.fetchData();
  },

  methods: {
    async fetchData() {
      try {
        const response = await http.get('rezerwacja');
        this.jsonData = response.data;
      } catch (error) {
        this.errorMessage = `Wystąpił błąd: ${error}`;
        console.error(error);
      }
    },
    editRow(row) {
      this.$router.push(`/rezerwacja_edit/${row}`);
    },
    delRow(row) {
      http.delete(`rezerwacja/${row}`)
          .then(response => {
            this.fetchData();
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    getRezerwacjaLink(id) {
      return `/rezerwacja_ksiazka_list/${id}`;
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
    <router-link to="/rezerwacja_edit" class="btn btn-primary">Nowa Rezerwacja</router-link>
    <br><br>
    <vue-good-table class="custom-table"
        :columns="columns"
        :rows="jsonData">
      <template #table-row="props">
        <span v-if="props.column.field === 'id_rezerwacja'">
          <router-link :to="getRezerwacjaLink(props.row.id_rezerwacja)" class="text-primary">{{props.row.id_rezerwacja}}</router-link>
        </span>
        <span v-else-if="props.column.field === 'akcje'">
          <button class="btn btn-primary" @click="editRow(props.row.id_rezerwacja)">Edytuj</button>
          <button class="btn btn-danger" @click="delRow(props.row.id_rezerwacja)">Usuń</button>
        </span>
      </template>
    </vue-good-table>
  </div>
</template>