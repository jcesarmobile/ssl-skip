import { WebPlugin } from '@capacitor/core';

import type { SslSkipPlugin } from './definitions';

export class SslSkipWeb extends WebPlugin implements SslSkipPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
