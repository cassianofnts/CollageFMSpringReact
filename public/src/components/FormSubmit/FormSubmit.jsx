import './FormSubmit.css';

import axios from 'axios';
import React from 'react';

class FormSubmit extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      period: '7days',
      limit: 9,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    const body = JSON.stringify(this.state);
    const headers = {
      'Content-Type': 'application/json' ,
    }
    axios
      .post('http://localhost:8080/api/collage', body, { headers })
      .then((response) => console.log(response.data))
      .catch((error) => console.log(error));
  }

  handleChange(event) {
    this.setState({
      [event.currentTarget.id]: event.currentTarget.value,
    });
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label htmlFor="username" className="params">
          Username:
        </label>
        <input
          placeholder="username"
          name="username"
          id="username"
          className="params"
          onChange={this.handleChange}
        />

        <select name="period" id="period" className="params" onChange={this.handleChange}>
          {periodOptions.map((periodOptions, index) => (
            <option key={index} value={periodOptions.value}>
              {periodOptions.label}
            </option>
          ))}
        </select>

        <select name="limit" id="limit" className="params" onChange={this.handleChange}>
          {limitOptions.map((limitOptions, index) => (
            <option value={limitOptions.value} key={index}>
              {limitOptions.label}
            </option>
          ))}
        </select>

        <button type="submit" className="params">
          gerar imagem
        </button>
      </form>
    );
  }
}

const periodOptions = [
  {
    label: '7 dias',
    value: '7days',
  },
  {
    label: '1 mes',
    value: '1month',
  },
  {
    label: '3 meses',
    value: '3month',
  },
  {
    label: '6 meses',
    value: '6month',
  },
  {
    label: '12 meses',
    value: '12month',
  },
  {
    label: 'Desde Sempre',
    value: 'overall',
  },
];

const limitOptions = [
  {
    label: '3x3',
    value: '9',
  },
  {
    label: '4x4',
    value: '16',
  },
  {
    label: '5x5',
    value: '25',
  },
];

export default FormSubmit;
