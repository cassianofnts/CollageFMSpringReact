import './App.css';

import FormSubmit from './components/FormSubmit/FormSubmit';

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Collage FM</h1>
        <h2>
          Verifique seus albuns mais escutados na <a href="//last.fm">Last.FM</a>
        </h2>
      </header>
      <article>
        <FormSubmit />
      </article>
    </div>
  );
};

export default App;
